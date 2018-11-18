$(window).on('load', function() {

  // Row Toggler
  // -----------------------------------------------------------------
  // language=JQuery-CSS
  $('#demo-foo-row-toggler').footable();

  // Accordion
  // -----------------------------------------------------------------
  $('#demo-foo-accordion').footable().on('footable_row_expanded', function(e) {
    $('#demo-foo-accordion tbody tr.footable-detail-show').not(e.row).each(function() {
      $('#demo-foo-accordion').data('footable').toggleDetail(this);
    });
  });

  // Pagination
  // -----------------------------------------------------------------
  $('#demo-foo-pagination').footable();
  $('#demo-show-entries').change(function (e) {
    e.preventDefault();
    var pageSize = $(this).val();
    $('#demo-foo-pagination').data('page-size', pageSize);
    $('#demo-foo-pagination').trigger('footable_initialized');
  });
});
