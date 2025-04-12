SRC_DIR = src
BUILD_DIR = build
DOCS_DIR = docs

JAVAC = javac
JAVA = java
JAVADOC = javadoc
CLASSPATH = $(BUILD_DIR)

SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)

all: $(CLASSES)

$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(BUILD_DIR)
	$(JAVAC) -d $(BUILD_DIR) $(SOURCES)

javadoc:
	@mkdir -p $(DOCS_DIR)
	$(JAVADOC) -d $(DOCS_DIR) $(SOURCES)

run: all
	@$(JAVA) -cp $(CLASSPATH) Main

run1: all
	@$(JAVA) -cp $(CLASSPATH) Main < test1.txt

run2: all
	@$(JAVA) -cp $(CLASSPATH) Main < test2.txt

re: clean all

clean:
	rm -rf $(BUILD_DIR)/* $(DOCS_DIR)/* $(BUILD_DIR) $(DOCS_DIR)

.PHONY: all javadoc run clean