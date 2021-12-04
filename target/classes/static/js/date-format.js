var today = new Date();
var tomorrow = new Date(Date.now() + 1000 * 3600 * 24);
var result = tomorrow
    .toLocaleString('en-US', { weekday: 'long', month: 'numeric', year: 'numeric', day: 'numeric' });