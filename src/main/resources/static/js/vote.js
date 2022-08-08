$(document).ready(function() {
    $('.vote-up').submit(function(e) {
        e.preventDefault();

        const postId = $(this).data('id');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify( { "voteType": "UPVOTE", "postId": postId}) ,
            url: '/api/posts/' + postId + '/upvote',
            success: function(data) {
                console.log('voted up!');
                setInterval('location.reload()', 200);
            },
            error: function(err) {
                console.log(err.messsage);

            }
        });
    });
    $('.vote-down').submit(function(e) {
        e.preventDefault();

        const postId = $(this).data('id');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify( { "voteType": "DOWNVOTE", "postId": postId}) ,
            url: '/api/posts/' + postId + '/upvote',
            success: function(data) {
                console.log('voted down!');
                setInterval('location.reload()', 200);
            },
            error: function(err) {
                console.log(err.messsage);
            }
        });
    });

});