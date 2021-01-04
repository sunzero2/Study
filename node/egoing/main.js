const HTTP = require('http'),
  FS = require('fs'),
  URL = require('url'),
  QS = require('querystring'),
  TEMPLATE = require('./lib/TEMPLATE.js'),
  PATH = require('path'),
  SANITIZE_HTML = require('sanitize-html');

const readBodyDom = (title, description) => {
  return `<h2>${title}</h2><p>${description}</p>`;
}

const readControlDom = (title) => {
  return `<a href="/create">create</a>
          <a href="/update?id=${title}">update</a>
          <form action="/delete_process" method="post">
            <input type="hidden" name="id" value="${title}">
            <input type="submit" value="delete">
          </form>
          `
}

const updateBodyDom = (title, description) => {
  return `
        <form action="/update_process" method="post">
          <input type="hidden" name="id" value="${title}">
          <p>
            <input type="text" name="title" placeholder="title" value="${title}">
          </p>
          <p>
            <textarea name="description" placeholder="description">${description}</textarea>
          </p>
          <p>
            <input type="submit">
          </p>
        </form>
        `
}

const updateControlDom = (title) => {
  return `<a href="/create">create</a> <a href="/update?id=${title}">update</a>`;
}

const createDOM = (queryData, keyword, callback) => {
  FS.readdir('data', function (error, filelist) {
    const filteredId = PATH.parse(queryData.id).base;
    FS.readFile(`data/${filteredId}`, 'utf8', (err, description) => {
      const title = queryData.id,
        sanitizedTitle = SANITIZE_HTML(title),
        sanitizedDescription = SANITIZE_HTML(description, {
          allowedTags: ['h1']
        }),
        list = TEMPLATE.list(filelist),
        body = keyword === 'read' ? readBodyDom(sanitizedTitle, sanitizedDescription) : updateBodyDom(sanitizedTitle, sanitizedDescription),
        control = keyword === 'read' ? readControlDom(sanitizedTitle) : updateControlDom(sanitizedTitle);
      html = TEMPLATE.html(title, list, body, control);
      
      callback();
    });
  });
}

const app = HTTP.createServer((request, response) => {
  const _url = request.url,
    queryData = URL.parse(_url, true).query,
    pathName = URL.parse(_url, true).pathname;

  if (pathName === '/') {
    if (queryData.id === undefined) {
      FS.readdir('data', (error, filelist) => {
        const title = 'welcome',
          description = 'Hello, node.js',
          list = TEMPLATE.list(filelist),
          html = TEMPLATE.html(title, list,
            `<h2>${title}</h2><p>${description}</p>`,
            `<a href="/create">create</a>`);
        response.writeHead(200);
        response.end(html);
      });
    } else {
      createDOM(queryData, 'read', () => {
        response.writeHead(200);
        response.end(html);
      });
    }
  } else if (pathName === '/create') {
    FS.readdir('data', (error, filelist) => {
      const title = 'WEB - create',
        list = TEMPLATE.list(filelist),
        html = TEMPLATE.html(title, list, `
      <form action="/create_process" method="post">
        <p>
          <input type="text" name="title" placeholder="title">
        </p>
        <p>
          <textarea name="description" placeholder="description"></textarea>
        </p>
        <p>
          <input type="submit">
        </p>
      </form>
      `, '');

      response.writeHead(200);
      response.end(html);
    });
  } else if (pathName === '/create_process') {
    let body = '';
    request.on('data', (data) => {
      body += data;
    });

    request.on('end', () => {
      const post = QS.parse(body),
        title = post.title,
        description = post.description;
      
      FS.writeFile(`data/${title}`, description, 'utf8', (error) => {
        response.writeHead(302, { Location: `/?id=${title}` });
        response.end();
      });
    });
  } else if (pathName === '/update') {
    createDOM(queryData, 'update', () => {
      response.writeHead(200);
      response.end(html);
    });
    
  } else if (pathName === '/update_process') {
    let body = '';
    request.on('data', (data) => {
      body += data;
    });

    request.on('end', () => {
      const post = QS.parse(body),
        id = post.id,
        title = post.title,
        description = post.description;

      FS.rename(`data/${id}`, `data/${title}`, (error) => {
        FS.writeFile(`data/${title}`, description, 'utf8', (error) => {
          response.writeHead(302, { Location: `/?id=${title}` });
          response.end();
        });
      });
    });
  } else if (pathName === '/delete_process') {
    let body = '';
    request.on('data', (data) => {
      body += data;
    });

    request.on('end', () => {
      const post = QS.parse(body),
        id = post.id,
        filteredId = PATH.parse(id).base;

      FS.unlink(`data/${filteredId}`, (error) => {
        response.writeHead(302, { Location: '/' });
        response.end();
      });
    });
  } else {
    response.writeHead(404);
    response.end('Not found');
  }
});

app.listen(3000);

