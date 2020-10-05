function showHighScores() {
	
	
	if ($(".highscore").css("display")=="none") {
		$(".highscore").css("display","block");
		$(".highscoreText").text("Hide high scores")
	}else {
		$(".highscore").css("display","none");
		$(".highscoreText").text("Show high scores")
	}
	
}
