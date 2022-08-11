const url = 'http://localhost:8080/api/users';

let roles = [
    {
        id: 4,
        name: "ROLE_ADMIN",
        authority: "ROLE_ADMIN"
    },
    {
        id: 5,
        name: "ROLE_USER",
        authority: "ROLE_USER"
    }
]

let  currentRoles = []

async function getUsers() {
    fetch(url)
        .then(res => {
            res.json().then(
                data => {
                    console.log(data);
                    if (data.length > 0) {
                        let temp = '';
                        data.forEach((user) => {
                            temp += "<tr>";
                            temp += "<td>" + user.id + "</td>";
                            temp += "<td>" + user.username + "</td>";
                            temp += "<td>" + user.lastName + "</td>";
                            temp += "<td>" + user.age + "</td>";
                            temp += "<td>" + user.mail + "</td>";
                            temp += `<td>${user.rolesToString}</td>`;
                            temp += `<td><button type="button" class="btn btn-info" style="color: white" data-bs-toggle="modal" data-bs-target="#editModal" data-bs-userId=${user.id} data-bs-userName=${user.name} data-bs-userLastname=${user.lastName} data-bs-userAge=${user.age} data-bs-userEmail=${user.mail} data-bs-userEmail=${user.roles} data-bs-userEmail=${user.password}>Edit</button></td>`
                            temp += `<td><button type="button" class="btn btn-danger" style="color: white" 
                                                data-bs-toggle="modal" data-bs-target="#delete" 
                                                data-bs-userId=${user.id} data-bs-Username=${user.name} 
                                                data-bs-userLastname=${user.lastName} data-bs-userAge=${user.age} 
                                                data-bs-userEmail=${user.mail} data-bs-userRole=${user.rolesToString}>Delete</button></td></tr>`
                            console.log("from get user " + user.name)
                        })
                        document.getElementById('js-usersData').innerHTML = temp;
                    }
                }
            )
        })
}
getUsers()