
const toTitleCase = (camelCaseText) => {
    const result = camelCaseText.replace(/([A-Z])/g, " $1");
    const titleCase = result.charAt(0).toUpperCase() + result.slice(1);
    return titleCase;
}

module.exports = {toTitleCase}