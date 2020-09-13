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

const memberPosition = member.map(m => m.position);
console.log(memberPosition);

// map() 메서드를 사용하지 않을 경우
function getPosition(m) {
    let memberPositionArray = [];
    for(let i = 0; i < m.length; i++) {
        memberPositionArray[i] = m[i].position;
    }

    return memberPositionArray;
}

const memberPosition2 = getPosition(member);
console.log(memberPosition2);