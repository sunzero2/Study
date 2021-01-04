var M = {
    v: 'v',
    f: function () {
        console.log(this.v);
    }
}

var A = {
    v: 'v',
    f: function () {
        console.log(this.v);
    }
}

module.exports = [M, A];