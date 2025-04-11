# Directories
SRC_DIR = src
BUILD_DIR = build
DOCS_DIR = docs

# Compiler and flags
JAVAC = javac
JAVA = java
JAVADOC = javadoc
CLASSPATH = $(BUILD_DIR)

# Source files
SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)

# Default target
all: $(CLASSES)

# Compile only changed Java files
$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(BUILD_DIR)
	$(JAVAC) -d $(BUILD_DIR) $<

# Generate Javadoc
javadoc:
	@mkdir -p $(DOCS_DIR)
	$(JAVADOC) -d $(DOCS_DIR) $(SOURCES)

# Run the program
run: all
	$(JAVA) -cp $(CLASSPATH) Main

# Clean build and docs directories
clean:
	rm -rf $(BUILD_DIR)/* $(DOCS_DIR)/* $(BUILD_DIR) $(DOCS_DIR)

.PHONY: all javadoc run clean