let locationID = document.getElementById("id");
let locationName = document.getElementById("name");
let longitude = document.getElementById("longitude");
let latitude = document.getElementById("latitude");


function allLocation(){
        fetch('http://localhost:8070/rest/location/all')
            .then(resp => resp.json())
            .then(resp => resp.forEach(item => {
                    let param1 = document.createElement("p");
                    param1.style.marginLeft = '20px';
                    param1.style.paddingRight = '25px'
                    /*param1.setAttribute("margin-left","200px")*/
                    param1.innerText = item.id;
                    locationID.append(param1);
                    let param2 = document.createElement("p");
                    param2.innerText = item.name;
                    param2.style.marginLeft = '20px';
                    param2.style.paddingRight = '25px'
                    locationName.append(param2);
                    let param3 = document.createElement("p");
                    param3.innerText = item.longitude;
                    param3.style.marginLeft = '20px';
                    param3.style.paddingRight = '25px'
                    latitude.append(param3);
                    let param4 = document.createElement("p");
                    param4.innerText = item.latitude;
                    param4.style.marginLeft = '20px';
                    param4.style.paddingRight = '25px'
                    longitude.append(param4);
            }));
}

async function test(event) {
        event.preventDefault;
        const formData = new FormData(event.target);
        formData.append("name",document.getElementById("name").value);
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

function log(){
      console.log(JSON.stringify(data))
}




