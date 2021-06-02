$(document).on('click', '.card-group', function() {
    let id = $(this).find("input").val();
    document.getElementById("id_livro").value = id;
    document.getElementById("formlivros").submit();
    // Para mandar para o servlet, so fazer
});
