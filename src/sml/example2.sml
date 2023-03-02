     mov EAX 2
     mov EBX 3
     mov ECX 1
     mov EDX 1
tag: mul EAX EBX
     sub EBX EDX
     add ECX EDX
     jnz EBX tag
     out EAX