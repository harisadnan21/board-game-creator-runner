## Description

The logicelement has two classes with protected constructors, preventing them from being used

## Expected Behavior

IsPieceTypeRay and Outflank can be constructed anywhere.

## Current Behavior

IsPieceTypeRay and Outflank cannot be used outside of their packages. We did not realize this from the tests because the test class was declared to be in the same package

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Try to construct instance of these classes outside of their package
1. observe no constructor exists

## Hypothesis for Fixing the Bug

Change protected to public