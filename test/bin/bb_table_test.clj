(ns bin.bb-table-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.java.shell :as shell]))

(deftest help-option
  ;; Using bin/bb-table b/c setting $PATH doesn't work with current CI setup
  (let [cmd-results (shell/sh "bin/bb-table" "-h"
                              :env {"BABASHKA_CLASSPATH" "src"
                                    "PATH" (str "bin:" (System/getenv "PATH"))})
        expected-results (-> (io/resource "bin/bb_table_test/help-option.edn")
                             slurp
                             edn/read-string)]
    (is (= (:exit expected-results) (:exit cmd-results)))
    (is (= (:out expected-results) (:out cmd-results)))
    (is (= (:err expected-results) (:err cmd-results)))))
