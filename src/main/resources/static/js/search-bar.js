const loggerList = document.getElementById('allStatList')
const searchBar = document.getElementById('searchInput')

const allStats = [];

fetch("http://localhost:8080/statistics/search")
    .then(response => response.json())
    .then(data => {
        for (let stat of data) {
            allStats.push(stat);
        }
    })

console.log(allStats);

searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredData = allStats.filter(stat => {
        return stat.user.toLowerCase().includes(searchingCharacters)
          ;
    });
    console.log(filteredData);
    displayStats(filteredData);
})



const displayStats = (stat) => {
    loggerList.innerHTML = stat
        .map((s) => {
            return `
        <tr style="color: #fefefe">
            <td>${s.id}</td>
            <td>${s.ip}</td>
            <td>${s.page}</td>
            <td>${s.refererPage}</td>
            <td>${s.requestMethod}</td>
         
            <td>${s.timestamp}</td>
            <td>${s.url}</td>        
            <td>${s.user}</td>
            <td>${s.userAgent}</td>
        </tr>
            `
        })
        .join('');
}
