SRC_DIR := .

OUT_DIR := build/classes
LIB_DIR := Library

MAIN := dm.joxel.Joxel

.PHONY: all clean compile run

all: clean compile run

clean:
	rm -f $$(find $(OUT_DIR) -name *.class)
	rm -rf $(OUT_DIR)/*

compile:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $$(find $(SRC_DIR) -name *.java) -cp $$(find $(LIB_DIR) -name "*.jar" -exec echo -n "{}:" \;)
run:
	java -Djava.library.path=$(LIB_DIR)/natives -cp $$(find $(LIB_DIR) -name "*.jar" -exec echo -n "{}:" \;)$(OUT_DIR) $(MAIN) 

jar: compile
	@echo "Manifest-Version: 1.0" > $(OUT_DIR)/manifest.txt
	@echo "Class-Path: ." >> $(OUT_DIR)/manifest.txt
	@echo "Main-Class: $(MAIN)" >> $(OUT_DIR)/manifest.txt
	@echo "" >> $(OUT_DIR)/manifest.txt
	jar -cmf $(OUT_DIR)/manifest.txt $(OUT_DIR)/build.jar $$(find $(OUT_DIR) -name *.class)