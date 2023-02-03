function sortTableColumn(n) {
    const columnsId = new Map();

    columnsId.set(1, 'Title');
    columnsId.set(3, 'Project');
    columnsId.set(7, 'Criticality');
    columnsId.set(8, 'Category');
    columnsId.set(9, 'Priority');
    columnsId.set(10, 'Status');
    columnsId.set(11, 'Created On');

    columnsId.forEach(key => {
        document.getElementById(key).innerHTML = key
    });

    const criticality = new Map([['Blocker', 1], ['Severe', 2], ['High', 3], ['Medium', 4], ['Low', 5]]);
    const priority = new Map([['Low', 1], ['Medium', 2], ['High'], ['Urgent', 4]]);
    const status = new Map([['Done', 1], ['In-progress', 2], ['Open', 3]]);

    const sortAZ = "<i class=\"bi bi-caret-up-fill ps-1\"></i>";
    const sortZA = "<i class=\"bi bi-caret-down-fill ps-1\"></i>";

    let table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("reports");
    switching = true;
    dir = "asc";

    while (switching) {

        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {

            shouldSwitch = false;

            if (n == 7) {
                x = criticality.get(rows[i].getElementsByTagName("TD")[n].innerHTML);
                y = criticality.get(rows[i + 1].getElementsByTagName("TD")[n].innerHTML)
            } else if (n == 9) {
                x = priority.get(rows[i].getElementsByTagName("TD")[n].innerHTML);
                y = priority.get(rows[i + 1].getElementsByTagName("TD")[n].innerHTML)
            } else if (n == 10) {
                x = status.get(rows[i].getElementsByTagName("TD")[n].innerHTML);
                y = status.get(rows[i + 1].getElementsByTagName("TD")[n].innerHTML)
            } else {
                x = rows[i].getElementsByTagName("TD")[n].innerHTML.toLowerCase();
                y = rows[i + 1].getElementsByTagName("TD")[n].innerHTML.toLowerCase();
            }

            if (dir == "asc") {
                document.getElementById(columnsId.get(n)).innerHTML = columnsId.get(n) + sortAZ;
                if (x > y) {

                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                document.getElementById(columnsId.get(n)).innerHTML = columnsId.get(n) + sortZA;
                if (x < y) {

                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {

            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;

            switchcount++;
        } else {

            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}