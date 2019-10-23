TextContent textBody =                                                 textContent()
    .add(p().add(
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla accumsan, metus ultrices eleifend gravida, nulla nunc varius lectus, nec rutrum justo nibh eu lectus. Ut vulputate semper dui. Fusce erat odio, sollicitudin vel erat vel, interdum mattis neque. Sub works as well!"))
    .add(p().add("Quisque ante lacus, malesuada ac auctor vitae, congue ")
    .add(a().add("non ante").attr("href", "#"))
    .add(". Phasellus lacus ex, semper ac tortor nec, fringilla condimentum orci. Fusce eu rutrum tellus."))
    .add(blockquote().add(
    "Ut venenatis, nisl scelerisque sollicitudin fermentum, quam libero hendrerit ipsum, ut blandit est tellus sit amet turpis."))
    .add(small()
    .add("Sometimes you need small text to display things like date created")));

