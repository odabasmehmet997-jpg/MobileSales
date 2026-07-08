package okio.internal;

import java.io.IOException;
import java.util.Iterator;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import kotlinx.coroutines.CoroutineScope;
import okio.FileSystem;
import okio.Path;

final class _FileSystemKtcommonListRecursively1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    final  Path dir;
    final  boolean followSymlinks;
    final  FileSystem this_commonListRecursively;
    private   Object L0;
    Object L1;
    Object L2;
    int label;
    _FileSystemKtcommonListRecursively1(Path path, FileSystem fileSystem, boolean z, Continuation<? super _FileSystemKtcommonListRecursively1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.dir = path;
        this.this_commonListRecursively = fileSystem;
        this.followSymlinks = z;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        _FileSystemKtcommonListRecursively1 _filesystemkt_commonlistrecursively_1 = new _FileSystemKtcommonListRecursively1(this.dir, this.this_commonListRecursively, this.followSymlinks, (Continuation<? super _FileSystemKtcommonListRecursively1>) continuation);
        _filesystemkt_commonlistrecursively_1.L0 = obj;
        return _filesystemkt_commonlistrecursively_1;
    }
    public Object invoke(Object p0) {
        return null;
    }
    public Unit invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        try {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    public  Object invokeSuspend(Object obj)  {
        SequenceScope sequenceScope;
        ArrayDeque arrayDeque;
        Iterator<Path> it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            SequenceScope sequenceScope2 = (SequenceScope) this.L0;
            ArrayDeque arrayDeque2 = new ArrayDeque();
            arrayDeque2.addLast(this.dir);
            sequenceScope = sequenceScope2;
            arrayDeque = arrayDeque2;
            try {
                it = this.this_commonListRecursively.list(this.dir).iterator();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) this.L2;
            ArrayDeque arrayDeque3 = (ArrayDeque) this.L1;
            SequenceScope sequenceScope3 = (SequenceScope) this.L0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            arrayDeque = arrayDeque3;
            sequenceScope = sequenceScope3;
        }
        while (it.hasNext()) {
            Path next = it.next();
            FileSystem fileSystem = this.this_commonListRecursively;
            boolean z = this.followSymlinks;
            this.L0 = sequenceScope;
            this.L1 = arrayDeque;
            this.L2 = it;
            this.label = 1;
            try {
                if (_FileSystemKt.collectRecursively(sequenceScope, fileSystem, arrayDeque, next, z, false, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return Unit.INSTANCE;
    }
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }
}
