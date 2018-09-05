# github comment settings
github.dismiss_out_of_range_messages

# Warn when there is a big PR
warn("Big PR, try to keep changes smaller if you can") if git.lines_of_code > 500

 checkstyle_format.base_path = Dir.pwd


# detekt
# Detekt 1.0.0.RC5-6 include root project folder in file name
checkstyle_format.report 'selenium-test/target/checkstyle-result.xml'

# Android Lint
# require 'android_lint_translate_checkstyle_format'
# android_lint_xml = ::AndroidLintTranslateCheckstyleFormat::Script.translate(File.read('selenium-test/target/checkstyle-result.xml'))
# checkstyle_format.report_by_text android_lint_xml

# Findbugs
require 'findbugs_translate_checkstyle_format'
findbugs_xml = ::FindbugsTranslateCheckstyleFormat::Script.translate(File.read('selenium-test/target/checkstyle-result.xml'))
checkstyle_format.report_by_text findbugs_xml

# PMD
require 'pmd_translate_checkstyle_format'
pmd_xml = ::PmdTranslateCheckstyleFormat::Script.translate(File.read('selenium-test/target/checkstyle-result.xml'))
checkstyle_format.report_by_text pmd_xml

# PMD-CPD
require 'pmd_translate_checkstyle_format'
pmd_cpd_xml = ::PmdTranslateCheckstyleFormat::Script.translate_cpd(File.read('selenium-test/target/checkstyle-result.xml'))
checkstyle_format.report_by_text pmd_cpd_xml