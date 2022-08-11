const editModal = document.getElementById('editModal')
const modalUserId = editModal.querySelector('#editId')
const modalUserName = editModal.querySelector('#editName')
const modalUserLastname = editModal.querySelector('#editLastname')
const modalUserAge = editModal.querySelector('#editAge')
const modalUserEmail = editModal.querySelector('#editEmail')



editModal.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget
    const userId = button.getAttribute('data-bs-userId')
    const userName = button.getAttribute('data-bs-userName')
    const userLastname = button.getAttribute('data-bs-userLastname')
    const userAge = button.getAttribute('data-bs-userAge')
    const userEmail = button.getAttribute('data-bs-userEmail')



    modalUserId.value = userId
    modalUserName.value = userName
    modalUserLastname.value = userLastname
    modalUserAge.value = userAge
    modalUserEmail.value = userEmail
})


const editId = document.getElementById('editId')
const editUsername = document.getElementById('editName')
const editLastname = document.getElementById('editLastname')
const editAge = document.getElementById('editAge')
const editEmail = document.getElementById('editEmail')
const editPassword = document.getElementById('editPassword')
const editModalForm = document.querySelector('.editModalForm')



editModalForm.addEventListener('submit', e => {
    e.preventDefault();


    const formData = new FormData(editForm)
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
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
            id: editId.value,
            name: editUsername.value,
            lastName: editLastname.value,
            age: editAge.value,
            mail: editEmail.value,
            password: editPassword.value,
            roles: currentRoles
        })
    }).then(()=> getUsers())
    $("#editModal").modal("hide");
    editModalForm.reset();
})