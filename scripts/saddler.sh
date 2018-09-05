#!/usr/bin/env bash

if [ -z "${CI_PULL_REQUEST}" ]; then
    # when not pull request
    REPORTER=Saddler::Reporter::Github::CommitReviewComment
else
    REPORTER=Saddler::Reporter::Github::PullRequestReviewComment
fi

echo "********************"
echo "* android lint     *"
echo "********************"
cat checkstyle-result.xml \
    | bundle exec android_lint_translate_checkstyle_format translate \
    | bundle exec checkstyle_filter-git diff origin/master \
    | bundle exec saddler report --require saddler/reporter/github --reporter $REPORTER