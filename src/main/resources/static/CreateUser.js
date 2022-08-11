const name = document.getElementById('name')
const lastname = document.getElementById('lastname')
const age = document.getElementById('age')
const email = document.getElementById('email')
const password = document.getElementById('password')
const addForm = document.querySelector('.addForm')

addForm.addEventListener('submit', e => {
    e.preventDefault();

    const formData = new FormData(createUser)
    let rolesName = []

    formData.forEach((key) => {
        if (key.includes('ROLE')) {
            rolesName.push(key)
        }
    })

    if (rolesName.length === 1) {
        if (rolesName[0] === 'ROLE_ADMIN') {
            currentRoles.push(roles[0])
        } else {
            currentRoles.push(roles[0])
        }
    } else {
        currentRoles = roles
    }

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
            name: name.value,
            lastName: lastname.value,
            age: age.value,
            mail: email.value,
            password: password.value,
            roles: currentRoles
        })
    }).then(()=> getUsers())
        .then(() => addForm.reset())

    return show('showUsers','addUser')
})
