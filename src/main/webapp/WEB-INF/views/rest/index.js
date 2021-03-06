let id = document.getElementById("id");
let name = document.getElementById("name");
let longitude = document.getElementById("longitude");
let latitude = document.getElementById("latitude");
let locations = document.getElementById("locations")
let regions = document.getElementById("regions");
let buttons = document.getElementById('buttons');
let button = document.getElementById('button');
let surname = document.getElementById('surname');
let age = document.getElementById('age');
let deleteButton = document.getElementById('deleteButton');
let updateButton = document.getElementById('updateButton');

function allLocation() {
    fetch('http://localhost:8070/rest/location/all')
        .then(resp => resp.json())
        .then(resp => resp.forEach(item => {
            let param1 = document.createElement("p");
            param1.innerText = item.id;
            id.append(param1);
            let param2 = document.createElement("p");
            param2.innerText = item.name;
            name.append(param2);
            let param3 = document.createElement("p");
            param3.innerText = item.longitude;
            latitude.append(param3);
            let param4 = document.createElement("p");
            param4.innerText = item.latitude;
            longitude.append(param4);
            let param5 = document.createElement('button');
            param5.addEventListener('click', () => redirecttodetailinfoaboutlocation(item.id));
            param5.style.margin = '7px';
            param5.style.paddingBottom = '5px';
            param5.style.display = 'table';
            param5.innerText = 'Detail info';
            button.append(param5);
            let param6 = document.createElement('button');
            param6.addEventListener('click',() => redirectUserForUpdateLocation(item.id));
            param6.style.margin = '7px';
            param6.style.paddingBottom = '5px';
            param6.style.display = 'table';
            param6.innerText = 'Update info';
            updateButton.append(param6);
            let param7 = document.createElement('button');
            param7.addEventListener('click',() => deleteLocation(item.id));
            param7.style.margin = '7px';
            param7.style.paddingBottom = '5px';
            param7.style.display = 'table';
            param7.innerText = 'Delete location';
            deleteButton.append(param7);
        }));
}

async function test(event) {
    event.preventDefault;
    const formData = new FormData(event.target);
    formData.append("name", document.getElementById("name").value);
    const datas = {};
    formData.forEach((value, key) => (datas[key] = value));
    const resp = fetch('http://localhost:8070/rest/region', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datas)

    });
    return await resp.json();
}

function createUser() {
    fetch('http://localhost:8070/rest/location/all')
        .then(resp => resp.json())
        .then(resp => resp.forEach(item => {
            let option = document.createElement("option");
            option.innerText = item.name;
            option.value = item.id;
            locations.append(option)
        }))
}

async function sendUser(event) {
    event.preventDefault;
    const formData = new FormData(event.target);
    formData.append("name", document.getElementById("name").value);
    formData.append("surname", document.getElementById("surname").value);
    formData.append("age", document.getElementById("age").value);
    formData.append("locationId", document.getElementById("locations").value)
    const datas = {};
    formData.forEach((value, key) => (datas[key] = value));
    const resp = fetch('http://localhost:8070/rest/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datas)

    });
    return await resp.json();
}

function getRegion() {
    fetch('http://localhost:8070/rest/region/all')
        .then(resp => resp.json())
        .then(resp => resp.forEach(item => {
            let option = document.createElement('option');
            option.innerText = item.name;
            option.value = item.id;
            regions.append(option)
        }));
}

async function addLocation() {
    const formData = new FormData();
    formData.append("locationName", document.getElementById("name").value);
    formData.append("longitude", document.getElementById("longitude").value);
    formData.append("latitude", document.getElementById("latitude").value);
    formData.append("regionId", document.getElementById("regions").value);

    let regions = document.querySelector('#regions')
    const selectedValues = [].filter
        .call(regions.options, option => option.selected)
        .map(option => option.innerText);
    formData.append('regionName', selectedValues[0]);


    const datas = {};
    formData.forEach((value, key) => (datas[key] = value));
    const resp = fetch('http://localhost:8070/rest/location', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datas)

    });
    return await resp.json();
}

function getInfoAboutAllRegion() {
    fetch('http://localhost:8070/rest/region/all')
        .then(resp => resp.json())
        .then(resp => resp.forEach(item => {
            let param1 = document.createElement("p");
            param1.style.marginLeft = '20px';
            param1.style.paddingRight = '25px'
            param1.innerText = item.id;
            id.append(param1);
            let param2 = document.createElement("p");
            param2.innerText = item.name;
            param2.style.marginLeft = '20px';
            param2.style.paddingRight = '25px';
            name.append(param2);
            let param3 = document.createElement('button');
            param3.addEventListener('click', () => redeirectUserToInfoAbouRegion(item.id));
            param3.style.margin = '7px';
            param3.style.paddingBottom = '5px';
            param3.style.display = 'table';
            param3.innerText = 'Detail info';
            button.append(param3);
            let param4 = document.createElement('button');
            param4.addEventListener('click', () => redirectUserForUpdateRegion(item.id));
            param4.style.margin = '7px';
            param4.style.paddingBottom = '5px';
            param4.style.display = 'table';
            param4.innerText = 'Update region';
            updateButton.append(param4);
            let param5 = document.createElement('button');
            param5.addEventListener('click', () => deleteRegion(item.id));
            param5.style.margin = '7px';
            param5.style.paddingBottom = '5px';
            param5.style.display = 'table';
            param5.innerText = 'Delete region';
            deleteButton.append(param5);
        }))
}

function infoAboutUsers() {
    fetch('http://localhost:8070/rest/user/all')
        .then(resp => resp.json())
        .then(resp => resp.forEach((item, idx) => {
            let param1 = document.createElement("p");
            param1.style.marginLeft = '20px';
            param1.style.paddingRight = '25px'
            param1.innerText = item.id;
            id.append(param1);
            let param2 = document.createElement("p");
            param2.innerText = item.name;
            param2.style.marginLeft = '20px';
            param2.style.paddingRight = '25px'
            name.append(param2);
            let param6 = document.createElement('p');
            param6.innerText = item.surname;
            surname.append(param6);
            let param3 = document.createElement('p');
            const value = item.locationInfoDto.name;
            param3.innerText = value;
            locations.append(param3);
            let param4 = document.createElement('button');
            param4.addEventListener('click', () => redirect(item.id));
            param4.style.margin = '7px';
            param4.style.paddingBottom = '5px';
            param4.style.display = 'table';
            param4.innerText = 'Show detail info about user';
            buttons.append(param4);
            let param5 = document.createElement('button');
            param5.addEventListener('click', () => redirectForUpdateUser(item.id));
            param5.style.margin = '7px';
            param5.style.paddingBottom = '5px';
            param5.style.display = 'table';
            param5.innerText = 'Update info about user';
            button.append(param5);
            let param7 = document.createElement('button');
            param7.addEventListener('click', () => deleteUser(item.id));
            param7.style.margin = '7px';
            param7.style.paddingBottom = '5px';
            param7.style.display = 'table';
            param7.innerText = 'Delete user';
            deleteButton.append(param7);
        }))
}

function redirect(ident) {
    localStorage.setItem('id', ident);
    window.location.href = '/src/main/webapp/WEB-INF/views/rest/user/userdetailinfo.html';

}

function detailsInfo() {
    let userId = localStorage.getItem('id');
    fetch('http://localhost:8070/rest/user/' + userId)
        .then(resp => resp.json())
        .then(resp => {
            let userId = document.createElement("p");
            userId.style.marginLeft = '20px';
            userId.style.paddingRight = '25px'
            userId.innerText = resp.id;
            id.append(userId);
            let userName = document.createElement('p');
            userName.innerText = resp.name;
            name.append(userName);
            let surName = document.createElement('p');
            surName.innerText = resp.surname;
            surname.append(surName);
            let userLocation = document.createElement('p')
            userLocation.innerText = 'location name: ' + resp.locationInfoDto.name + ' longitude: ' + resp.locationInfoDto.longitude
                + ' latitude: ' + resp.locationInfoDto.latitude;
            locations.append(userLocation);
        })
}

function redirectForUpdateUser(ident) {
    localStorage.setItem('id', ident);
    window.location.href = '/src/main/webapp/WEB-INF/views/rest/user/updateuserinfo.html';
}

function currentUserInfo() {
    let userId = localStorage.getItem('id');
    fetch('http://localhost:8070/rest/user/' + userId)
        .then(resp => resp.json())
        .then(resp => {
            name.value = resp.name;
            surname.value = resp.surname;
            age.value = resp.age;
        })
}

async function updateUser() {
    let userId = localStorage.getItem('id');
    const formData = new FormData();
    const datas = {};
    formData.append('name', name.value);
    formData.append('surname', surname.value);
    formData.append('age', age.value)
    formData.forEach((value, key) => (datas[key] = value));
    const resp = fetch('http://localhost:8070/rest/user/' + userId, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datas)
    });
    return await resp.json();
}

function deleteUser(ident) {
    fetch('http://localhost:8070/rest/user/' + ident, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(() => window.location.reload(), error => {
        alert('UPS! smth went wrong...')
    });
}

function redeirectUserToInfoAbouRegion(ident) {
    localStorage.setItem('id', ident);
    window.location.href = '/src/main/webapp/WEB-INF/views/rest/region/detailinfoaboutregion.html'
}

function getDetailInfoAboutRegion() {
    let ident = localStorage.getItem('id');
    fetch('http://localhost:8070/rest/region/' + ident)
        .then(resp => resp.json())
        .then(resp => {
            let locationName = document.createElement('p');
            locationName.innerText = resp.name;
            name.append(locationName);
            let locationId = document.createElement('p');
            locationId.innerText = resp.id;
            id.append(locationId);

            resp.locationInfoDtoList.forEach(location => {
                let locationOptions = document.createElement('option');
                locationOptions.value = resp.id;
                locationOptions.innerText = 'Location name: ' + location.name + ' latitude: ' + location.latitude + ' longitude ' + location.longitude;
                regions.append(locationOptions);
            })
        })
}

function redirectUserForUpdateRegion(ident) {
    localStorage.setItem('id', ident);
    window.location.href = '/src/main/webapp/WEB-INF/views/rest/region/updateregioninfo.html';
}

function getRegionForUpdate() {
    let ident = localStorage.getItem('id');
    fetch('http://localhost:8070/rest/region/' + ident)
        .then(resp => resp.json())
        .then(resp => name.value = resp.name)
}

async function updateRegion() {
    let ident = localStorage.getItem('id');
    const formData = new FormData();
    const datas = {};
    formData.append('name', name.value);
    formData.forEach((value, key) => (datas[key] = value));
    const resp = fetch('http://localhost:8070/rest/region/' + ident, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datas)
    });
    return await resp.json();
}

function deleteRegion(ident) {
    fetch('http://localhost:8070/rest/region/' + ident, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(() => window.location.reload(), error => {
        alert('UPS! smth went wrong...')
    });
}

function detailInfoAboutLocation() {
    fetch('http://localhost:8070/rest/location/' + localStorage.getItem('id'))
        .then(resp => resp.json())
        .then(resp => {
            id.innerText = resp.id;
            name.innerText = resp.name;
            longitude.innerText = resp.longitude;
            latitude.innerText = resp.latitude;
            });
}

function redirecttodetailinfoaboutlocation(ident) {
    localStorage.setItem('id', ident);
    window.location.href = '/src/main/webapp/WEB-INF/views/rest/location/detailinfoaboutlocation.html';
}

async function updateCurrentLocation(){
    const formData = new FormData();
    const datas = {};
    formData.append('name', name.value);
    formData.append('latitude',latitude.value);
    formData.append('longitude',longitude.value);
    formData.forEach((value, key) => (datas[key] = value));
    const resp = fetch('http://localhost:8070/rest/location/' + localStorage.getItem('id'), {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datas)
    });
    return await resp.json();
}

function redirectUserForUpdateLocation(indent){
    localStorage.setItem('id',indent);
    window.location.href='/src/main/webapp/WEB-INF/views/rest/location/updateLocationInfo.html';
}

function getValueAboutLocationForForm(){
    if(navigator.onLine){
    fetch('http://localhost:8070/rest/location/' + localStorage.getItem('id'))
        .then(resp => resp.json())
        .then(resp => {
            name.value = resp.name;
            latitude.value = resp.latitude;
            longitude.value = resp.longitude;
        })
    }else {
        alert('UPS! Something went wrong')
    }

}

function deleteLocation(ident) {
    if(navigator.onLine) {
        fetch('http://localhost:8070/rest/location/' + ident, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }else {
        alert('UPS! Something went wrong');
    }
}
