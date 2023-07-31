function formInput(props, index) {

    if (props.type === 'select') {
        return formSelect(props, index)
    }
    let pattern = '';
    if(props.pattern){
        pattern = 'pattern="' + props.pattern + '"';
    }
    console.log(pattern);
    return `<div class="${props.classDiv}">
                <label>${props.label}</label>
                    <input class="input-custom form-control"
                    type="${props.type || 'text'}"
                    name="${props.name}"
                    onblur="onFocus(${index})" 
                    ${pattern}
                    value="${props.value}"
                    ${props.require ? 'required' : ''} /></br>
                <span class="error">${props.message}</span>
            </div>`
}
function formSelect(props, index) {
    let optionsStr = "";
    props.options.forEach(e => {
        if(props.value === e.name){
            optionsStr += `<option value="${e.value}" selected>${e.name}</option>`;
        }else {
            optionsStr += `<option value="${e.value}" >${e.name}</option>`;
        }
    })

    return `<div class="${props.classDiv}">
                <label>${props.label}</label>
                    <select class="input-custom form-control"
                    type="${props.type || 'text'}" name="${props.name}"
                    onblur="onFocus(${index})"
                    value="${props.value}"
                    ${props.require ? 'required' : ''}>
                        <option value>---Choose---</option>
                        ${optionsStr}
                    </select>
                    </br>
                    <span class="error">${props.message}</span>
            </div>`
}
const onFocus = (index) => {
    const inputsForm = document.querySelectorAll('#formBody .input-custom')
    inputsForm[index].setAttribute("focused", "true");
}

document.addEventListener('invalid', (function () {
    return function (e) {
        e.preventDefault();
        e.target.onblur();
    };
})(), true);