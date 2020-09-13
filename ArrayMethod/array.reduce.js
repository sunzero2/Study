const member = [
    {
        name: 'Kim',
        position: 'developer'
    },
    {
        name: 'Lee',
        position: 'designer'
    },
    {
        name: 'Bak',
        position: 'developer'
    },
    {
        name: 'Hwang',
        position: 'planner'
    }
];

const developerCnt = member.reduce((count, m) => {
    if(m.position === 'developer') {
        count++;
    }
    
    return count;
}, 0);

console.log(developerCnt);



const test = [10, 1, 2, 3];
const total = test.reduce(reducer, 5);

function reducer(previousValue, currentValue) {
    return previousValue + currentValue;
};

//console.log(total);