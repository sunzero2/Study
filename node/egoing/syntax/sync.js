var fs = require('fs');

// Node.js는 기본적으로 비동기를 권장한다.
// readFileSync
console.log('A');
var result = fs.readFileSync('syntax/sample.txt', 'utf8');
console.log(result);
console.log('C');

// readFIle
console.log('aA');
fs.readFile('syntax/sample.txt', 'utf8', function(err, res) {
    console.log(res);
});
console.log('aC');