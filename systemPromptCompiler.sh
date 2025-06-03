#!/bin/bash

grep -v '^\s*#' systemPrompt.yaml | yq eval '.. | select(tag == "!!str")' - > systemPrompt.txt