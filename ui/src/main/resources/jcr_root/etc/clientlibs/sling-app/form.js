document.getElementById('postData').addEventListener('submit', postData)

function postData(event){
    event.preventDefault()

    let locationDestination = document.getElementById('location-destination').value,
        locationPickup = document.getElementById('location-pickup').value,
        policyNum = document.getElementById('policy-num').value,
        timePickup = document.getElementById('time-pickup').value,
        timeDropoff = document.getElementById('time-dropoff').value,
        driver = document.getElementById('driver').value,
        cost = document.getElementById('cost').value

    // console.log(locationDestination)
    // console.log(locationPickup)
    // console.log(policyNum)
    // console.log(timePickup)
    // console.log(timeDropoff)
    // console.log(driver)
    // console.log(cost)
    const fucker = JSON.stringify({
        locationDestination:`${locationDestination}`,
        locationPickup:`${locationPickup}`,
        policyNum:`${policyNum}`,
        timePickup:`${timePickup}`,
        timeDropoff:`${timeDropoff}`,
        driver:`${driver}`,
        cost:`${cost}`
    })
    
    console.log('fucker', fucker);

    fetch('/appointment-creator', {
        method: 'POST',
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            locationDestination:`${locationDestination}`,
            locationPickup:`${locationPickup}`,
            policyNum:`${policyNum}`,
            timePickup:`${timePickup}`,
            timeDropoff:`${timeDropoff}`,
            driver:`${driver}`,
            cost:`${cost}`
        })
    })
    .then((res) => res.json())
    // .then((res) => res)
    .then((data) =>  console.log('data', data))
    .catch((err)=> console.log(err))
}
