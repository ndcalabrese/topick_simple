$(document).ready(function() {
    $('.vote-up').submit(function(e) {
        // e.preventDefault();

        const postId = $(this).data('id');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify( { "voteType": "UPVOTE", "postId": 1}) ,
            url: '/api/posts/' + postId + '/upvote',
            success: function(data) {
                console.log('voted up!');
            },
            error: function(err) {
                console.log(err.messsage);
            }
        });
    });
    $('.vote-down').submit(function(e) {
        // e.preventDefault();

        const postId = $(this).data('id');
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify( { "voteType": "DOWNVOTE", "postId": 1}) ,
            url: '/api/posts/' + postId + '/upvote',
            success: function(data) {
                console.log('voted down!');
            },
            error: function(err) {
                console.log(err.messsage);
            }
        });
    });
});