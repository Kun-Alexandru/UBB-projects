function getAllTopics() {
    $.getJSON("topic-servlet", {action: "getAll"},
        function (response) {
            console.log(response);

            $('#topics-table').html("");
            $('#topics-table').append(
                "<thead>" +
                "<tr>" +
                "<th style='visibility: collapse;' scope=\"col\"> Topic Id </th>" +
                "<th scope=\"col\"> Name </th>" +
                "<th scope=\"col\"> Comments </th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>");

            for (var topic in response) {
                topic = response[topic];
                console.log(topic);
                $('#topics-table').append(
                    "<tr>" +
                    "<td style='visibility: collapse;'>" + topic.id + "</td>" +
                    "<td>" + topic.name + "</td>" +
                    '<td><button type="button" class="detailsButton"> View Comments </button></td>' +
                    "</tr>"
                )
            }

            $('#topics-table').append(
                "</tbody>"
            )
        }
    )
}

function viewComments(topicId) {
    // set the current topicId in the comment-servlet
    $.post("comment-servlet", {action: "setCurrentTopicId", topicId: topicId},
        function (data) {
            console.log(data);
            // alert(data);
        });

    // redirect to the oneTopic.jsp page
    window.location = "oneTopic.jsp";
}

$(document).ready(function () {
    getAllTopics();
    $("#add-topic-form").hide();

    $("#topics-table").on("click", ".detailsButton", function (event) {
        var tr = $(this).closest('tr');
        var topicId = tr.find('td').eq(0).html();
        viewComments(topicId);
    });

    $("#add-topic-button").click(function(){
        $("#add-topic-form").show();
    });

    $("#add-topic-button-submit").on('click', function(){
        var nameForm = $('#name').val();
        $.post("topic-servlet", {action: "add", name: nameForm, userId: sessionUserId},
            function (data) {
                getAllTopics();
                console.log(data);
            });

        // hide the form
        $("#add-topic-form").hide();

        getAllTopics();
    });
});
