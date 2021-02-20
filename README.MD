==================================================================
# Author - Devansh Singh

############################################################################
# THIS IS ONLY THE BACKEND CODE. THE UI CODE CAN BE FOUND HERE:-
https://github.com/devansh1497/Image-dropzone-UI-/tree/main/src
#############################################################################

This is a Spring Boot project which accepts a JPG/PNG/GIF from each user/client.
and uploads this image to the Amazon S3 bucket.
When the UI is loaded, it retrieves the selected image for each user.

Points to remember:-
You need an AWS account. To connect to the AWS servers, you need a secret key
and access key. You can google about it for more info.

Basically, we need to consume these 2 keys in our backend code to connect to AWS.
This code fetches these keys from the enviorment variables - AWSACCESSKEY & AWSSECRETKEY.
You would need to create these 2 variables in your enviorment and assign them the values of your AWS Access key and the secret key. 
It is different for everyone.
You could use some other values for these values too but make sure to 
configure them in the AwsKeys enum which is then being used in the AwsConfig.java file.

