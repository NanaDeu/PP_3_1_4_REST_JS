const userHeader = document.getElementById("userHeader");
const userTable = document.getElementById("userTable");

let userResponse = () => {
    fetch("http://localhost:8080/api/user", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })
        .then(response => response.json())
        .then(user => {

            let roles = user.roles.map(role => role.name);

            userHeader.innerHTML = `
                    <p class="d-inline font-weight-bold">${user.username} </p>
                    <p class="d-inline"> with roles: ${roles}</p>`

            userTable.innerHTML = `
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
userResponse();