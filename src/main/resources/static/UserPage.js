$(async function () {
    await loadUser();
});

async function loadUser() {
    fetch("http://localhost:8080/api/user")
        .then(r => r.json())
        .then(data => {
            $('#navUsername').append(data.email);
            let roles = data.roles.map(role => " " + role.role.substring(5));
            $('#navRoles').append(roles);


            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.username}</td>
                <td>${data.name}</td>
                <td>${data.age}</td>
                <td>${data.phoneNumber}</td>
                <td>${roles}</td>)`;
            $('#userPanelBody').append(user);
        })
        .catch((error) => {
            alert(error);
        });
}