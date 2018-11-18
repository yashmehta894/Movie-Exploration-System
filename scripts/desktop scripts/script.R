library(Rserve,lib="E:/R-3.3.1/library")
Rserve()

library(twitteR,lib="E:/R-3.3.1/library")
library(RCurl,lib="E:/R-3.3.1/library")
library(RJSONIO,lib="E:/R-3.3.1/library")
library(tm,lib="E:/R-3.3.1/library")
library(wordcloud,lib="E:/R-3.3.1/library")


api_key="ZHNpAzVQPI9B8EBxLTtce1EXM" 
api_secret="pXyGL7cbhu40CXr3t6w5mJgChvOIYCiaMIhur8p6J42tRB9t5Z" 
token="755618382683119617-ugGXztnUVHuOeAt5HRAAC839h1FJd4S" 
token_secret="xoP1GO85mzMbCYyUFyLUJsmzr0M84wQ0drTazk42huKtP" 

setup_twitter_oauth(api_key, api_secret, token, token_secret)

movie<- searchTwitter(movie_name, n=100,lang="en")

movie_text<- sapply(movie,function(x) x$getText())

movie_text <- gsub("(RT|via)((?:\\b\\W*@\\w+)+)", "", movie_text)
movie_text <- gsub("@\\w+", "", movie_text)
movie_text <- gsub("[[:punct:]]", "", movie_text)
movie_text <- gsub("[[:digit:]]", "", movie_text)
movie_text <- gsub("http\\w+", "", movie_text)
movie_text <- gsub("[ \t]{2,}", "", movie_text)
movie_text <- gsub("^\\s+|\\s+$", "", movie_text)

movie_corpus<- Corpus(VectorSource(movie_text))
movie_clean<- tm_map(movie_corpus,removePunctuation)
movie_clean<- tm_map(movie_clean,removeWords,stopwords("en"))
movie_clean<- tm_map(movie_clean,removeNumbers)
movie_clean<- tm_map(movie_clean,stripWhitespace)
movie_clean<- tm_map(movie_clean,content_transformer(tolower))
a.dtm1<-TermDocumentMatrix(movie_clean, control = list(wordLengths = c(4,10))) 
N=10
m<-as.matrix(a.dtm1)
v=sort(rowSums(m), decreasing=TRUE)
write.csv(movie_text, file="D:/list.csv")
png("D:/MachineLearningCloud.png")
wordcloud(movie_text,random.order=T,color=rainbow(50))
dev.off()
