document.getElementById('appt-creator--form').addEventListener('submit', postData)
const apptModal = document.querySelector('.appt-created--modal'),
    apptLink = apptModal.querySelector('.review-new-appt'),
    apptCloseModal = apptModal.querySelector('.close-modal')

apptCloseModal.addEventListener('click', closeModal)

function closeModal() {
    event.preventDefault()
    apptModal.close()
}

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
        }))
        .catch((err)=> console.log(err))
}