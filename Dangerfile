# github comment settings
github.dismiss_out_of_range_messages

# Warn when there is a big PR
warn("Big PR, try to keep changes smaller if you can") if git.lines_of_code > 500

# detekt
# Detekt 1.0.0.RC5-6 include root project folder in file name
checkstyle_format.base_path = File.basename(Dir.getwd)
checkstyle_format.report 'target/checkstyle-result.xml'

# Android Lint
 checkstyle_format.base_path = Dir.pwd
 require 'android_lint_translate_checkstyle_format'
 android_lint_xml = ::AndroidLintTranslateCheckstyleFormat::Script.translate(File.read('target/checkstyle-result.xml'))
 checkstyle_format.report_by_text
