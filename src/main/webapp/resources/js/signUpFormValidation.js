$(document).ready(function() {
    $('#singUpForm').bootstrapValidator({
        fields: {
            firstName: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 60,
                        message: 'the first name is too long...',
                    },
                    notEmpty: {
                        message: 'Please supply your login name',
                    },
                    regexp: {
                        regexp: /^[\w\d_-]+$/i,
                        message: 'The login name can consist of alphabetical characters and numbers only'
                    }
                }
            },
            lastName: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 60,
                        message: 'the last name length is too long...',
                    },
                    notEmpty: {
                        message: 'Please supply your login name',
                    },
                    regexp: {
                        regexp: /^[\w\d-_]+$/i,
                        message: 'The login name can consist of alphabetical characters and numbers only'
                    }
                }
            },
            email: {
                validators: {
                    stringLength: {
                        max: 60,
                        message: 'the email is too long...'
                    },
                    emailAddress: {
                        message: 'The value is not a valid email address'
                    },
                    notEmpty: {
                        message: 'Please supply your email',
                    }
                }
            },
            password: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 40,
                        message: 'the password is too long...',
                    },
                    notEmpty: {
                        message: 'Please supply your password',
                    }
                }
            }
        }
    });
});