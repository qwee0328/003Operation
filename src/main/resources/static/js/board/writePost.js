$(document).ready(function(){
/*	 $('.postArea__summernote').summernote({
	    placeholder:"내용을 입력해주세요",
	    height:500,
	    disableResizeEditor: true,
	    lang: 'ko-KR',
	    maximumFileSize: 10485760,
	    toolbar: [
	        ['style', ['style']],
	        ['font', ['bold', 'underline', 'clear']],
	        ['color', ['color']],
	        ['para', ['ul', 'ol', 'paragraph']],
	        ['table', ['table']],
	        ['insert', ['picture']],
	        ['view', ['help']]
	    ]
	});*/
                        
    $(".fileInput__label").on("click",function(){
        $(".postArea__fileInput").trigger("click");
    });
});