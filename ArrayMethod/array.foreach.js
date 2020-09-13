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

member.forEach(m => {
    if(m.position !== 'developer') {
        console.log(`${m.name}님은 개발자가 아닙니다.`);
    } else {
        console.log(`${m.name}님은 개발자입니다.`);
    }
});