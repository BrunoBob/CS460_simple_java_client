COMPIL = javac
FLAG =
NAME = Client

all: $(NAME).class

$(NAME).class: $(NAME).java
	$(COMPIL) $(FLAG) $(NAME).java

clean:
	rm $(NAME).class
