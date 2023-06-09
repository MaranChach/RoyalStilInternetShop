const select = document.getElementById("sortSelect")
const sortType = new URL(document.location).searchParams.get("sortType")

for (let i, j = 0; i = select.options[j]; j++) {
    if (i.value == sortType) {
        select.selectedIndex = j;
        break;
    }
}