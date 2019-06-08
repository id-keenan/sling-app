document.getElementById('postData').addEventListener('submit', postData)

function postData(event) {
    event.preventDefault()

    fetch('/appointment-creator', {
        method: 'POST',
        body: new FormData(document.getElementById('appointment-creator--form'))
    })
    .then((res) => res)
    .catch((err)=> console.log(err))
}
