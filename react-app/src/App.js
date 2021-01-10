import './App.css';
import React, { Component } from 'react';
import TOC from './components/TOC';
import ReadContent from './components/ReadContent';
import CreateContent from './components/CreateContent';
import UpdateContent from './components/UpdateContent';
import Subject from './components/Subject';
import Control from './components/Control';

class App extends Component {
  constructor(props) {
    super(props);
    this.max_content_id = 3;
    this.state = {
      mode: 'welcome',
      selected_content_id: 2,
      subject: { title: 'WEB', sub: 'world wide web!' },
      welcome: { title: 'Welcome', desc: 'Hello, React!!!' },
      contents: [
        { id: 1, title: 'HTML', desc: 'HTML is information' },
        { id: 2, title: 'CSS', desc: 'CSS is for design' },
        { id: 3, title: 'JavaScript', desc: 'JavaScript is for interactive' },
      ]
    }
  }

  render() {
    let _title, _desc, _article = null;

    switch (this.state.mode) {
      case 'welcome':
        _title = this.state.welcome.title;
        _desc = this.state.welcome.desc;
        _article = <ReadContent title={_title} des={_desc} />
        break;
      case 'read':
        _title = this.state.contents[this.state.selected_content_id - 1].title;
        _desc = this.state.contents[this.state.selected_content_id - 1].desc;
        _article = <ReadContent title={_title} des={_desc} />
        break;
      case 'create':
        _article = <CreateContent onSubmit={(_title, _desc) => {
          this.setState({
            contents: this.state.contents.concat({ id: ++this.max_content_id, title: _title, desc: _desc }),
            mode: 'read',
            selected_content_id: this.max_content_id
          });
        }} />
        break;
      case 'update':
        const _data = this.state.contents[this.state.selected_content_id - 1];
        _article = <UpdateContent data={_data} onSubmit={(_id, _title, _desc) => {
          let contents = [...this.state.contents];
          contents[_id - 1] = { id: _id, title: _title, desc: _desc };
          this.setState({contents, mode: 'read'});
        }} />
        break;
    }

    return (
      <div className="App">
        <Subject onChangePage={() => this.setState({ mode: 'welcome' })} title={this.state.subject.title} sub={this.state.subject.sub} />
        <TOC onChangePage={(id) => {
          this.setState({
            mode: 'read',
            selected_content_id: id
          })
        }} data={this.state.contents} />
        <Control onChangeMode={(mode) => {
          if (mode === 'delete') {
            if (window.confirm('Really?')) {
              let contents = [...this.state.contents];
              contents.splice(this.state.selected_content_id - 1, 1);
              this.setState({ mode: 'welcome', contents});
            }
          } else {
            this.setState({ mode });
          }
        }} />
        {_article}
      </div>
    )
  }
}
export default App;