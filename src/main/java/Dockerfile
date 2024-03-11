FROM bellsoft/liberica-openjdk-debian:17-cds
COPY . /tmp
WORKDIR /tmp
RUN javac -sourcepath . -d /tmp/out Main.java
WORKDIR /tmp/out
CMD ["java", "Main", "notes.bin"]
