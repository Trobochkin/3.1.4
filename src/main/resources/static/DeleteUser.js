const deleteModal = document.getElementById('delete')

let button
let idFromButton

deleteModal.addEventListener('show.bs.modal', event => {
    button = event.relatedTarget
    idFromButton = button.getAttribute('data-bs-userId')
    const deleteUserName = button.getAttribute('data-bs-userName')
    const deleteUserLastname = button.getAttribute('data-bs-userLastname')
    const deleteUserAge = button.getAttribute('data-bs-userAge')
    const deleteUserEmail = button.getAttribute('data-bs-userEmail')
    console.log(button.getAttribute('data-bs-userRole'))
    const deleteUserRole = button.getAttribute('data-bs-userRole')



    const deleteModalUserId = deleteModal.querySelector('#deleteId')
    const deleteModalUserName = deleteModal.querySelector('#deleteFirstname')
    const deleteModalUserLastname = deleteModal.querySelector('#deleteLastname')
    const deleteModalUserAge = deleteModal.querySelector('#deleteAge')
    const deleteModalUserEmail = deleteModal.querySelector('#deleteEmail')
    const deleteModalUserRole = deleteModal.querySelector('#deleteRole')



    deleteModalUserId.value = idFromButton
    deleteModalUserName.value = deleteUserName
    deleteModalUserLastname.value = deleteUserLastname
    deleteModalUserAge.value = deleteUserAge
    deleteModalUserEmail.value = deleteUserEmail
    deleteModalUserRole.value = deleteUserRole
})


const deleteModalForm = document.querySelector('.deleteForm')
deleteModalForm.addEventListener('submit', submit => {
    submit.preventDefault();

    fetch(url + '/' + idFromButton, {
        method: 'DELETE',
    }).then(()=> getUsers())
    $("#delete").modal("hide");
    deleteModalForm.reset();
})