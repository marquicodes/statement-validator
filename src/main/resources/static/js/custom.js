$(document).ready(function() {

  // Updates file upload label, to the selected filename.
  $('#file').on('change', function() {
    // gets file name
    var filename = this.files[0].name;
    $(this).next('.custom-file-label').html(filename);
  })
  
  $('#validation-results').DataTable();

});