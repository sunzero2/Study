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

const sortMemberName = [...member].sort((a, b) => {
    const nameA = a.name.toUpperCase();
    const nameB = b.name.toUpperCase();

    if(nameA > nameB) {
        return 1;
    }

    if(nameA < nameB) {
        return -1;
    }

    return 0;
});

console.log(sortMemberName);