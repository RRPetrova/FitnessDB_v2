function confirm(delForm, delUrl) { // <--- changed here
    if (confirm("Are you sure ?")) {
        delForm.action = delUrl;          // <--- changed here
        return true;                      // <--- changed here
    }
    return false;                         // <--- changed here
}