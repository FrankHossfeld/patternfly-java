package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.patternfly.client.components.TextContent.textContent;

import org.gwtproject.safehtml.shared.SafeHtmlUtils;
import org.patternfly.showcase.client.resources.Code;

class TextDocumentation extends ComponentDocumentation {

    TextDocumentation() {
        super(
                "Text",
                p().innerHtml(SafeHtmlUtils.fromTrustedString(
                        "The <b>text</b> component can wrap any static HTML content you want to place on your page to provide correct formatting when using standard HTML tags.<br><br>Text components such as Text, TextList, TextListItem need to be placed within a TextContent"))
                        .get(),
                asList(
                        new Demo(
                                "Headings",
                                Code.get().text_headings().getText(),
                                () -> div()
                                        .add(
                                                textContent()
                                                        .add(h(1, "Hello World"))
                                                        .add(h(2, "Second Level"))
                                                        .add(h(3, "Third Level"))
                                                        .add(h(4, "Forth Level"))
                                                        .add(h(5, "Fifth Level"))
                                                        .add(h(6, "Sixth Level")))
                                        .get()),
                        new Demo(
                                "Body text",
                                Code.get().text_body().getText(),
                                () -> div()
                                        .add(
                                                textContent()
                                                        .add(p().add(
                                                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla accumsan, metus ultrices eleifend gravida, nulla nunc varius lectus, nec rutrum justo nibh eu lectus. Ut vulputate semper dui. Fusce erat odio, sollicitudin vel erat vel, interdum mattis neque. Sub works as well!"))
                                                        .add(p().add("Quisque ante lacus, malesuada ac auctor vitae, congue ")
                                                                .add(a().add("non ante").attr("href", "#"))
                                                                .add(". Phasellus lacus ex, semper ac tortor nec, fringilla condimentum orci. Fusce eu rutrum tellus."))
                                                        .add(blockquote().add(
                                                                "Ut venenatis, nisl scelerisque sollicitudin fermentum, quam libero hendrerit ipsum, ut blandit est tellus sit amet turpis."))
                                                        .add(small()
                                                                .add("Sometimes you need small text to display things like date created")))
                                        .get()),
                        new Demo(
                                "Unorded list",
                                Code.get().text_unordered_list().getText(),
                                () -> div()
                                        .add(
                                                textContent()
                                                        .add(ul().add(li().add(
                                                                "In fermentum leo eu lectus mollis, quis dictum mi aliquet.")
                                                                .add(li().add(
                                                                        "Morbi eu nulla lobortis, lobortis est in, fringilla felis."))
                                                                .add(li().add(
                                                                        "Aliquam nec felis in sapien venenatis viverra fermentum nec lectus.")
                                                                        .add(ul().add(li().add(
                                                                                "In fermentum leo eu lectus mollis, quis dictum mi aliquet."))
                                                                                .add(li().add(
                                                                                        "Morbi eu nulla lobortis, lobortis est in, fringilla felis.")))))
                                                                .add(li().add("Ut non enim metus."))))
                                        .get()),
                        new Demo(
                                "Orded list",
                                Code.get().text_ordered_list().getText(),
                                () -> div()
                                        .add(
                                                textContent()
                                                        .add(ol().add(li().add("Donec blandit a lorem id convallis."))
                                                                .add(li().add("Cras gravida arcu at diam gravida gravida."))
                                                                .add(li().add("Integer in volutpat libero."))
                                                                .add(li().add("Donec a diam tellus."))
                                                                .add(li().add("Aenean nec tortor orci."))
                                                                .add(li().add(
                                                                        "Quisque aliquam cursus urna, non bibendum massa viverra eget."))
                                                                .add(li().add("Vivamus maximus ultricies pulvinar."))))
                                        .get()),
                        new Demo(
                                "Data list",
                                Code.get().text_data_list().getText(),
                                () -> div()
                                        .add(
                                                textContent()
                                                        .add(dl().add(dt().add("Web")).add(dd().add(
                                                                "The part of the Internet that contains websites and web pages"))
                                                                .add(dt().add("HTML"))
                                                                .add(dd().add("A markup language for creating web pages"))
                                                                .add(dt().add("CSS"))
                                                                .add(dd().add("A technology to make HTML look better"))))
                                        .get())));
    }
}