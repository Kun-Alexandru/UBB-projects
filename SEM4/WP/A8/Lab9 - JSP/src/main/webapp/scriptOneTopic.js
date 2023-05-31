function getAllComments() {
    $.getJSON("comment-servlet", {action: "getAll"},
        function (response) {
            console.log(response);

            $('#comments-section').html("");
            for (var comment in response) {
                comment = response[comment];
                console.log(comment);
                if(parseInt(comment.userid) == parseInt(sessionUserId))
                    $('#comments-section').append(
                        '<div class="card">' +
                        '<div class="card-header">' +
                        '<p>' + comment.username + ' posted</p>' +
                        '</div>' +
                        '<div class="card-body">' +
                        '<h5>' +
                        '<p hidden>' + comment.id + '</p>' +
                        '</h5>' +
                        '<p>' + comment.description + '</p>' +
                        '<p><button type="button" class="deleteButton"> Delete </button></p>' +
                        '</div>' +
                        '</div>' +
                        '<br>'
                    );
                else
                    $('#comments-section').append(
                        '<div class="card">' +
                        '<div class="card-header">' +
                        '<p>' + comment.username + ' posted</p>' +
                        '</div>' +
                        '<div class="card-body">' +
                        '<h5>' +
                        '<p hidden>' + comment.id + '</p>' +
                        '</h5>' +
                        '<p>' + comment.description + '</p>' +
                        '</div>' +
                        '</div>' +
                        '<br>'
                    );
            }
        }
    );
}

function deleteComment(commentId) {
    $.ajax({
        url: "comment-servlet?action=delete&commentId=" + commentId,
        type: "DELETE",
        success: function(data) {
            console.log(data);
            getAllComments(); // Call getAllComments() after the delete operation is completed
        },
        error: function(xhr, textStatus, errorThrown) {
            console.log("Error:", errorThrown);
        }
    });
}

$(document).ready(function () {
    getAllComments();
    $("#add-comment-form").hide();

    $("#comments-section").on("click", ".deleteButton", function(event) {
        var closestDiv = $(this).closest('div');
        var commentId = $(closestDiv).find('p').eq(0).html();

        var confirmation = confirm("Are you sure you want to delete this comment ?");
        if(confirmation === true) {
            deleteComment(commentId); // Call deleteComment() and wait for its completion
        }
    });

    $("#add-comment-button").click(function(){
        $("#add-comment-form").show();
    });

    $("#add-comment-button-submit").on('click', function(){
        var descriptionForm = $('#description').val();

        if (descriptionForm.trim() === '') {
            alert("Empty comment is bad!!"); // Display alert message
            return;
        }

        $.post("comment-servlet", {action: "add", description: descriptionForm, userId: sessionUserId},
            function (data) {
                console.log(data);
                getAllComments(); // Call getAllComments() after the add operation is completed

                // Empty the descriptionForm
                $('#description').val('');
            }
        );

        $("#add-comment-form").hide();
    });

});
