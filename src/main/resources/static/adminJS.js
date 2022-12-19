//For "User" button
const adminHeader = document.getElementById("adminHeader");
const userDescriptionTable = document.getElementById("userDescriptionTable");
let userDescription = () => {
    fetch("http://localhost:8080/api/user", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(user => {
            let roles = user.roles.map(role => role.name);
            adminHeader.innerHTML = `
                    <p class="d-inline font-weight-bold">${user.username} </p>
                    <p class="d-inline"> with roles: ${roles}</p>`
            userDescriptionTable.innerHTML = `
                    <tr>
                        <td> ${user.id} </td>
                        <td> ${user.name} </td>
                        <td> ${user.lastName} </td>
                        <td> ${user.age} </td>
                        <td> ${user.username} </td>
                        <td> ${roles} </td>
                    </tr>
                        `
        })
}
userDescription();

//For "Admin" button
//------------- ALL USERS ---------------
$(async function () {
    await getAllUsers();
});

async function getAllUsers() {
    const adminPageUsersTable = $('#adminPageUsersTable').empty();
    fetch("http://localhost:8080/api/users", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(users => {
            users.forEach((user) => {
                let output = `$(
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>${user.roles.map(role => role.name)}</td>
                    <td>
                        <!-- trigger-button for modal window EDIT-->
                        <button type="button" class="btn btn-sm bg-info text-white" data-toggle="modal"
                        id="editButton" data-target="editModal" data-id="${user.id}">
                            Edit
                        </button>
                    </td>
                    <td>
                        <!-- trigger-button for modal window DELETE -->
                        <button type="button" class="btn btn-sm bg-danger text-white" data-toggle="modal"
                                id="deleteButton" data-target="deleteModal" data-id="${user.id}">
                            Delete
                        </button>
                    </td>
                </tr>)
        `
                adminPageUsersTable.append(output)
            })
        })
}

// from Spanish video
const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

//------------- EDIT ---------------
//Function to get roles from DB
function getAllEditRoles() {
    const editRolesOptions = document.getElementById("editRoles");
    fetch("http://localhost:8080/api/roles", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(roles => {
            if (roles.length > 0) {
                let output = '';
                roles.forEach((role) => {
                    output += `
                 <option value="${role.id}">${role.name}</option>
        `
                })
                editRolesOptions.innerHTML = output
            }
        })
}

on(document, 'click', '#editButton', e => {
    const fila = e.target.parentNode.parentNode

    document.getElementById('editId').value = fila.children[0].innerHTML
    document.getElementById('editName').value = fila.children[1].innerHTML
    document.getElementById('editLastName').value = fila.children[2].innerHTML
    document.getElementById('editAge').value = fila.children[3].innerHTML
    document.getElementById('editUsername').value = fila.children[4].innerHTML
    document.getElementById('editPassword').value = ""
    document.getElementById('editRoles').value = getAllEditRoles()

    $("#editModal").modal("show")
})

$(async function () {
    editUser();
});

function editUser() {
    const editUserForm = document.querySelector('#editModal');

    /*function getUpdateUser() {
        if (document.getElementById('editPassword').value === null || document.getElementById('editPassword').value === "") {
            return {
                id: document.getElementById('editId').value,
                name: document.getElementById('editName').value,
                lastName: document.getElementById('editLastName').value,
                age: document.getElementById('editAge').value,
                username: document.getElementById('editUsername').value,
                roles: [{id: document.getElementById('editRoles').value}]
            }
        } else {
            return {
                id: document.getElementById('editId').value,
                name: document.getElementById('editName').value,
                lastName: document.getElementById('editLastName').value,
                age: document.getElementById('editAge').value,
                username: document.getElementById('editUsername').value,
                password: document.getElementById('editPassword').value,
                roles: [{id: document.getElementById('editRoles').value}]
            }
        }
    }*/

    editUserForm.addEventListener('submit', async e => {
        e.preventDefault()
        await fetch("http://localhost:8080/api/users/" + document.getElementById('editId').value, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }, body: JSON.stringify({
                id: document.getElementById('editId').value,
                name: document.getElementById('editName').value,
                lastName: document.getElementById('editLastName').value,
                age: document.getElementById('editAge').value,
                username: document.getElementById('editUsername').value,
                password: document.getElementById('editPassword').value,
                roles: [{id: document.getElementById('editRoles').value}]
            })
        })
            .then(() => {
                $("#editModal").modal("hide")
                getAllUsers()
            })
    })
}

//------------- DELETE ---------------
on(document, 'click', '#deleteButton', e => {
    const fila2 = e.target.parentNode.parentNode

    document.getElementById('deleteId').value = fila2.children[0].innerHTML
    document.getElementById('deleteName').value = fila2.children[1].innerHTML
    document.getElementById('deleteLastName').value = fila2.children[2].innerHTML
    document.getElementById('deleteAge').value = fila2.children[3].innerHTML
    document.getElementById('deleteUsername').value = fila2.children[4].innerHTML
    document.getElementById('deleteRolesOptions').text = fila2.children[5].innerHTML //text not value

    $("#deleteModal").modal("show")
})

$(async function () {
    deleteUser();
});

function deleteUser() {
    const deleteUserForm = document.querySelector('#deleteModal');
    deleteUserForm.addEventListener('submit', async e => {
        e.preventDefault();
        fetch('http://localhost:8080/api/users/' + document.getElementById('deleteId').value, {
            method: 'DELETE'
        })
            .then(() => {
                $("#deleteModal").modal("hide")
                getAllUsers()
            })
    })
}

//------------- NEW USER ---------------
// Getting the list of roles into options for new user form
function getAllRoles() {
    const allRoles = document.getElementById("roles");
    fetch("http://localhost:8080/api/roles", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(roles => {
            if (roles.length > 0) {
                let output = '';
                roles.forEach((role) => {
                    output += `
                 <option value="${role.id}">${role.name}</option>
        `
                })
                allRoles.innerHTML = output
            }
        })
}

document.getElementById('roles').value = getAllRoles();

$(async function () {
    await addNewUser();
});

async function addNewUser() {
    const addNewUser = document.querySelector('#addNewUser');
    addNewUser.addEventListener('submit', async (e) => {
        e.preventDefault();
        fetch("http://localhost:8080/api/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: document.getElementById('name').value,
                lastName: document.getElementById('lastName').value,
                age: document.getElementById('age').value,
                username: document.getElementById('username').value,
                password: document.getElementById('password').value,
                roles: [{id: document.getElementById('roles').value}]
            })
        })
            .then(() => {
                addNewUser.reset()
                getAllUsers()
                $('#userTableButton').click()
            })
    })
}