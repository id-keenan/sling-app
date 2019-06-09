const apptModal = document.querySelector('.appt-created--modal'),
    apptLink = apptModal.querySelector('.review-new-appt'),
    apptCloseModal = apptModal.querySelector('.close-modal'),
    apptFormInputs = ['location-destination','location-pickup','policy-num','date-pickup','time-pickup','time-dropoff','driver','cost']

// let resetForm = apptFormInputs.map((inputVal) => {
//     let temp = document.getElementById(inputVal)
//     return temp.value = ''
// })

class formUtils {
    constructor(arrInputs) {
        this.arrInputs = arrInputs
    }
    checkForm() {
        for (let i = 0, len = this.arrInputs.length; i < len; i++) {
            let temp = document.getElementById(this.arrInputs[i]).value.trim()
            temp ? console.log('shit') : console.log('fuck')
            // if (temp) {
            //     input.dataset.state = 'valid'
            // } else {
            //     input.dataset.state = 'invalid'
            // }
        }
    }
    clearForm() {
        for (let i = 0, len = this.arrInputs.length; i < len; i++) {
            let temp = document.getElementById(this.arrInputs[i])
            temp.value = ''
        }
    }
}

// const input = document.querySelector('.form-field input')
// input.addEventListener('input', evt => {
//     const value = input.value
//     if (!value) {
//         input.dataset.state = ''
//         return
//     }
//     const trimmed = value.trim()
//     if (trimmed) {
//         input.dataset.state = 'valid'
//     } else {
//         input.dataset.state = 'invalid'
//     }
// })

let resetFormInputs = function(arr) {
    for (let i = 0, len = arr.length; i < len; i++) {
        let temp = document.getElementById(arr[i])
        temp.value = ''
    }
}
// let testFunc = function(arr) {
//     for (let i = 0, len = arr.length; i < len; i++) {
//         // let temp = document.getElementById(arr[i])
//         let temp = document.getElementById(arr[i]).value.trim()
//         temp ? console.log('shit') : console.log('fuck')
//     }
// }
//
// testFunc(apptFormInputs)

document.getElementById('appt-creator--form').addEventListener('submit', postData)
apptCloseModal.addEventListener('click', closeModal)

function closeModal() {
    event.preventDefault()
    apptModal.close()
}

document.addEventListener('click', function(event) {
    if (event.target.closest('.appt-created--modal')) return;
    apptModal.close()
});

function postData(event) {
    event.preventDefault()

    fetch('/appointment-creator', {
        method: 'POST',
        body: new FormData(document.getElementById('appt-creator--form'))
    })
        .then((res) => res.json().then((data) => {
            let newPath = JSON.stringify(data.pagePath)
            newPath = newPath.replace(/"/gm, '')
            apptLink.href = `${newPath}.html`
            apptModal.showModal()
            resetFormInputs(apptFormInputs)
        }))
        .catch((err)=> console.log(err))
}