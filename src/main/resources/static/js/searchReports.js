function searchReports() {
    const columnNames = new Map();

    columnNames.set('id', 0);
    columnNames.set('title', 1);
    columnNames.set('desc', 2);
    columnNames.set('project', 3);
    columnNames.set('assignee', 4);
    columnNames.set('reporter', 5);
    columnNames.set('owner', 6);
    columnNames.set('criticality', 7);
    columnNames.set('category', 8);
    columnNames.set('priority', 9);
    columnNames.set('status', 10);
    columnNames.set('created', 11);

    let searchBarInput, searchFilter, searchKeyword, searchTerm, table, tr, td, txtValue;
    searchBarInput = document.getElementById("search-bar");
    searchFilter = searchBarInput.value.toUpperCase();
    table = document.getElementById("reports");
    tr = table.getElementsByTagName("tr");

    const splitExp = /\=/;
    searchKeyword = searchFilter.split(splitExp, 2)[0];
    searchTerm = searchFilter.split(splitExp, 2)[1];

    if (!searchFilter.includes("=") || searchTerm == undefined) {
        return;
    } else {
        for (const element of tr) {
            td = element.getElementsByTagName("td")[columnNames.get(searchKeyword.toLowerCase())];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(searchTerm) > -1) {
                    element.style.display = "";
                } else {
                    element.style.display = "none";
                }
            }
        }
    }
}
onload = (event) => searchReports();