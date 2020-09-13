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

const developer = member.find(m => m.position === 'developer');
console.log(developer);